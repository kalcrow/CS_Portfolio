/** 
  @file encrypt.c
  @author Kal Corwin
  Program that handles encrypting a given file based off of a given key
*/

#include "io.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ARG_NUM 4
#define KEY_INDEX 1
#define READ_INDEX 2
#define WRITE_INDEX 3

/**
  Main method which handles overarching functionality for encrypting files. 
  
  @param argc number of arguments given
  @param argv array of string arguments given
  @return 0 if the program runs successfully, another int value otherwise
*/
int main(int argc, char *argv[]) {
    
    if (argc != ARG_NUM) {
        fprintf(stderr, "%s\n", "usage: encrypt <key> <input_file> <output_file>");
    }
    
    byte key[ BLOCK_BYTES ];
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
    
    while (curr->len != 0) {
        //encrypt block
        encryptBlock(curr, K);
        
        //print block
        writeBlock(writeTo, curr);
        
        readBlock(fp, curr);
    }
    free(curr);
    fclose(fp);
    fclose(writeTo);
    return 0;
}