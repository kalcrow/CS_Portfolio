/** 
    @file decrypt.c
    @author Kal Corwin
    Program that handles encrypting a given file based off of a given key
*/

#include "io.h"
#include <stdio.h>
#include <stdlib.h>

#define ARG_NUM 4
#define KEY_INDEX 1
#define READ_INDEX 2
#define WRITE_INDEX 3

/**
  Main method which handles overarching functionality for decrypting files. 
  
  @param argc number of arguments given
  @param argv array of string arguments given
  @return 0 if the program runs successfully, another int value otherwise
*/
int main(int argc, char *argv[]) {
    
    if (argc != ARG_NUM) {
        fprintf(stderr, "%s\n", "usage: decrypt <key> <input_file> <output_file>");
        exit(1);
    }

    byte key[BLOCK_BYTES];
    prepareKey(key, argv[KEY_INDEX]);
    
    byte K[ ROUND_COUNT ][ SUBKEY_BYTES ];
    generateSubkeys(K, key);
    
    FILE *fp = fopen(argv[READ_INDEX], "rb");
    if (fp == NULL) {
        perror(argv[READ_INDEX]);
        exit(1);
    }
    
    FILE *writeTo = fopen(argv[WRITE_INDEX], "wb");
    if (writeTo == NULL) {
        fclose(fp);
        perror(argv[WRITE_INDEX]);
        exit(1);
    }
    
    DESBlock *curr = (DESBlock *)malloc(sizeof(DESBlock));
    readBlock(fp, curr);
    
    while (curr->len == BLOCK_BYTES) {

        //decrypt block
        decryptBlock(curr, K);

        //print stuff
        writeBlock(writeTo, curr);

        readBlock(fp, curr);
    }
    
    if (curr->len != 0) {
        fclose(fp);
        fclose(writeTo);
        free(curr);
        fprintf(stderr, "Bad ciphertext file length: %s\n", argv[READ_INDEX]);
        exit(1);
    }
    
    fclose(fp);
    fclose(writeTo);
    free(curr);
    return 0;
}