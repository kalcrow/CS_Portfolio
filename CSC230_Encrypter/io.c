/** 
  @file io.c
  @author Kal Corwin
  This program handles file IO for encrypt.c and decrypt.c. It reads/writes data in blocks of 8 bytes.
*/

#include "io.h"
#include <stdio.h>
#include <string.h>

/**
  Function that reads up to 8 bytes from the given filestream into the data field of block, 
  while also updating the len field of block to reflect how many bytes were read in
  
  @param fp filestream to read from
  @param block block to read into
*/
void readBlock( FILE *fp, DESBlock *block ){
    
    int length = 0;
    byte valIn;
    
    while ( length < BLOCK_BYTES && fread( &valIn, sizeof(byte), 1, fp ) != 0 ) {
        block->data[length] = valIn;
        length++;
    }
    
    block->len = length;
    
    while (length < BLOCK_BYTES){
        block->data[length] = 0x00;
        length++;
    }

    //close outside of io - encrypt/decrypt
}

/**
  Function that writes the data within the given block to the given filestream
  
  @param filestream to write to
  @param block block of data to write
*/
void writeBlock( FILE *fp, DESBlock const *block ){
  
    fwrite(block->data, sizeof(byte), block->len, fp);
    
    //deal with close outside of IO - encrypt/decrypt
    
}