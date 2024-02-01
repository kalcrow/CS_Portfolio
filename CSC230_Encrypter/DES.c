/** 
  @file DES.c
  @author Kal Corwin
  Implementation of the DES algorithm.
*/

#include "DES.h"
#include "DESMagic.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define C_D_COMBINED_SIZE 56

/**
  Function that takes a textKey string and a byte array to copy it into. If the
  testKey is too long, the function exits and prints a message to stderr.
  
  @param key byte array to copy into
  @param textKey key to copy from
*/
void prepareKey( byte key[ BLOCK_BYTES ], char const *textKey ){

    int properLength = BLOCK_BYTES;
    
    int length = (int)strlen(textKey);
    
    if (length > properLength) {
        fprintf(stderr, "%s\n", "Key too long");
        exit(1);
    }
    
    for (int i = 0; i < length; i++) {
        key[i] = *(textKey + i); 
    }
    
    if (length < properLength) {
        for (int i = length; i < properLength; i++) {
            key[i] = 0x00;
        }
    }
}

/**
  Function that returns the bit within data at a given index. If the index is out of bounds
  the function prints a message to stderr and exits with a status of 1.
  
  @param data byte array to find bit within
  @param idx index of bit to return
  @return the bit at the given index
*/
int getBit( byte const data[], int idx ){

    //gets the byte that holds the desired bit
    byte localOfBit = data[(idx - 1) / BYTE_SIZE];
    byte mask;

    if (idx % BYTE_SIZE == 0) {
        mask = 0x01;
    } else {
        mask = 0x01 << (BYTE_SIZE - (idx % BYTE_SIZE));
    }
    
    byte bitAlone = localOfBit & mask;
    
    if (bitAlone == 0x00) {
        return 0;
    } else {
        return 1;
    }
}

/**
  Function that sets the bit at the given index within data to the given value. 
  If the given index is out of bounds or the given value is invalid, the function
  exits and prints a message to stderr.
  
  @param data byte array to add bit to
  @param idx index to add bit at
  @param val value of bit to add
*/
void putBit( byte data[], int idx, int val ){
    
    if (val == 1) {
        //gets the byte that holds the desired bit
        byte localOfBit = data[(idx - 1) / BYTE_SIZE];
        byte mask;

        if (idx % BYTE_SIZE == 0) {
            mask = 0x01;
        } else {
            mask = 0x01 << (BYTE_SIZE - (idx % BYTE_SIZE));
        }

        byte bitAdded = localOfBit | mask;
        data[ (idx - 1) / BYTE_SIZE ] = bitAdded;
    } else {
        //gets the byte that holds the desired bit
        byte localOfBit = data[(idx - 1) / BYTE_SIZE];
        byte mask;

        switch (idx % BYTE_SIZE) {
            case 1: mask = 0x7F;
                    break;
            case 2: mask = 0xBF;
                    break;
            case 3: mask = 0xDF;
                    break;
            case 4: mask = 0xEF;
                    break;
            case 5: mask = 0xF7;
                    break;
            case 6: mask = 0xFB;
                    break;
            case 7: mask = 0xFD;
                    break;
            default: mask = 0xFE;
        }
        
        byte bitAdded = localOfBit & mask;
        data[ (idx - 1) / BYTE_SIZE ] = bitAdded;
    }
}

/**
  Function that copys n bits from the input string into output using getBit and putBit, which
  chooses the index to add each bit at based off of the first n values of perm. The function 
  ensures that, if n % BYTE_SIZE != 0, any bits that would not have been set are equal to 0.
  
  @param output array resulting from permutation
  @param input array to permute
  @param perm array that determines the order of permutation
  @param n how many values to permute
*/
void permute( byte output[], byte const input[], int const perm[], int n ){
    
    for (int i = 0; i < n / BYTE_SIZE; i++) {
        output[i] = 0x00;	
    }
    
    //checks if an extra byte is needed, and if it is then this sets it to 0x00 as well
    if (n % BYTE_SIZE != 0 ){
        output[ n / BYTE_SIZE] = 0x00;
    }
    
    for (int i = 1; i <= n; i++) {
    
        int bitIn = getBit(input, perm[i - 1]);
        
        putBit(output, i, bitIn);
    }

}

/**
  Function that uses the given key to generate 16 subkeys, stored in the given 2D array K starting at index 1
  
  @param K array to store subkeys in
  @param key key to generate subkeys from
*/
void generateSubkeys( byte K[ ROUND_COUNT ][SUBKEY_BYTES ], byte const key[ BLOCK_BYTES ] ){
    
    byte left[ SUBKEY_HALF_BYTES ];

    permute(left, key, leftSubkeyPerm, SUBKEY_HALF_BITS);
    
    byte right[ SUBKEY_HALF_BYTES ];
    permute(right, key, rightSubkeyPerm, SUBKEY_HALF_BITS);
    
    //C uses left
    byte C[ ROUND_COUNT ][ SUBKEY_HALF_BYTES ];
    //D uses right
    byte D[ ROUND_COUNT ][ SUBKEY_HALF_BYTES ];
    
    for (int i = 0; i < SUBKEY_HALF_BYTES; i++) {
        C[0][i] = left[i];
        D[0][i] = right[i];
    }
    
    for (int i = 1; i < ROUND_COUNT; i++) {
    
        //shift both left and right over one
        int cBit = getBit(left, 1);
        int dBit = getBit(right, 1);
        
        byte currL[1] = { left[SUBKEY_HALF_BYTES - 1] };
        byte currR[1] = { right[SUBKEY_HALF_BYTES - 1] };

        int tempCBit = getBit( currL, 1);
        int tempDBit = getBit( currR, 1);

        for (int k = 1; k < BYTE_SIZE / 2; k++) {
            putBit(currL, k, getBit(currL, k + 1));
        }
        putBit(currL, BYTE_SIZE / 2, cBit);

        for (int k = 1; k < BYTE_SIZE / 2; k++) {
            putBit(currR, k, getBit(currR, k + 1));
        }
        putBit(currR, BYTE_SIZE / 2, dBit);
        
        cBit = tempCBit;
        dBit = tempDBit;

        for (int k = (SUBKEY_HALF_BYTES - 1) * BYTE_SIZE + 1; k <= (SUBKEY_HALF_BYTES - 1) * BYTE_SIZE +  (BYTE_SIZE / 2); k++) {
            putBit(left, k, getBit(currL, k - ((SUBKEY_HALF_BYTES - 1) * BYTE_SIZE)));
        }
        	
        for (int k = (SUBKEY_HALF_BYTES - 1) * BYTE_SIZE + 1; k <= (SUBKEY_HALF_BYTES - 1) * BYTE_SIZE +  (BYTE_SIZE / 2); k++) {
            putBit(right, k, getBit(currR, k - ((SUBKEY_HALF_BYTES - 1) * BYTE_SIZE)));
        }
        
        for (int j = SUBKEY_HALF_BYTES - 2; j >= 0; j--) {
            byte currL[1] = { left[j] };
            byte currR[1] = { right[j] };

            tempCBit = getBit( currL, 1);
            tempDBit = getBit( currR, 1);
            
            for (int k = 1; k < BYTE_SIZE; k++) {
                 putBit(currL, k, getBit(currL, k + 1));
            }
            putBit(currL, BYTE_SIZE, cBit);
            
            for (int k = 1; k < BYTE_SIZE; k++) {
                putBit(currR, k, getBit(currR, k + 1));
            }
            putBit(currR, BYTE_SIZE, dBit);

            cBit = tempCBit;
            dBit = tempDBit;

            for (int k = j * BYTE_SIZE + 1; k <= j * BYTE_SIZE + BYTE_SIZE; k++) {
                putBit(left, k, getBit(currL, k - (j * BYTE_SIZE)));
            }
            
            for (int k = j * BYTE_SIZE + 1; k <= j * BYTE_SIZE + BYTE_SIZE; k++) {
                putBit(right, k, getBit(currR, k - (j * BYTE_SIZE)));
            }
        }
        
        if (subkeyShiftSchedule[i] == 2) {
            cBit = getBit(left, 1);
            dBit = getBit(right, 1);

            byte currL[1] = { left[SUBKEY_HALF_BYTES - 1] };
            byte currR[1] = { right[SUBKEY_HALF_BYTES - 1] };

            tempCBit = getBit( currL, 1);
            tempDBit = getBit( currR, 1);

            for (int k = 1; k < BYTE_SIZE/2; k++) {
                putBit(currL, k, getBit(currL, k + 1));
            }
            putBit(currL,  BYTE_SIZE/2, cBit);

            for (int k = 1; k <  BYTE_SIZE/2; k++) {
                putBit(currR, k, getBit(currR, k + 1));
            }
            putBit(currR,  BYTE_SIZE/2, dBit);

            cBit = tempCBit;
            dBit = tempDBit;

            for (int k = (SUBKEY_HALF_BYTES - 1) * BYTE_SIZE + 1; k <= (SUBKEY_HALF_BYTES - 1) * BYTE_SIZE + (BYTE_SIZE / 2); k++) {
                putBit(left, k, getBit(currL, k - ((SUBKEY_HALF_BYTES - 1) * BYTE_SIZE)));
            }
            
            for (int k = (SUBKEY_HALF_BYTES - 1) * BYTE_SIZE + 1; k <= (SUBKEY_HALF_BYTES - 1) * BYTE_SIZE + (BYTE_SIZE / 2); k++) {
                putBit(right, k, getBit(currR, k - ((SUBKEY_HALF_BYTES - 1) * BYTE_SIZE)));
            }

            for (int j = SUBKEY_HALF_BYTES - 2; j >= 0; j--) {
                byte currL[1] = { left[j] };
                byte currR[1] = { right[j] };

                tempCBit = getBit( currL, 1);
                tempDBit = getBit( currR, 1);

                for (int k = 1; k < BYTE_SIZE; k++) {
                    putBit(currL, k, getBit(currL, k + 1));
                }
                putBit(currL, 8, cBit);

                for (int k = 1; k < BYTE_SIZE; k++) {
                    putBit(currR, k, getBit(currR, k + 1));
                }
                putBit(currR, BYTE_SIZE, dBit);

                cBit = tempCBit;
                dBit = tempDBit;

                for (int k = j * BYTE_SIZE + 1; k <= j * BYTE_SIZE + BYTE_SIZE; k++) {
                    putBit(left, k, getBit(currL, k - (j * BYTE_SIZE)));
                }

                for (int k = j * BYTE_SIZE + 1; k <= j * BYTE_SIZE + BYTE_SIZE; k++) {
                    putBit(right, k, getBit(currR, k - (j * BYTE_SIZE)));
                }
            }
        }
        
        for (int j = 0; j < SUBKEY_HALF_BYTES; j++) {
            C[i][j] = left[j];
            D[i][j] = right[j];
        }
    }
    
    //now we have C and D, next we need to combine them
    byte temp[ C_D_COMBINED_SIZE / BYTE_SIZE ];
    
    
    for (int j = 1; j < ROUND_COUNT; j++) {

        //put C[j] and D[j] into one array
        for (int i = 1; i <= SUBKEY_HALF_BITS; i++) {
        
            putBit(temp, i, getBit(C[j], i));
        }
        
        for (int i = SUBKEY_HALF_BITS + 1; i <= C_D_COMBINED_SIZE; i++) {
            putBit(temp, i, getBit(D[j], i - SUBKEY_HALF_BITS));
        }
        
        //permute w/ subkeyPerm at n = 48 (SUBKEY_BITS)
        permute(K[ j ], temp, subkeyPerm, SUBKEY_BITS);
    } 
}

/**
  Function that uses the table sBoxTable to convert a 6 bit value from input starting at idx * 1 into
  a 4 bit value stored in the 4 highest order bits in output[1]

  @param output byte to store result in
  @param input byte array to get 6 bit value
  @param idx value from 0-7 reflecting the location of the 6 bit value in input
*/
void sBox( byte output[ 1 ], byte const input[ SUBKEY_BYTES ], int idx ){

    //literal constants are fine within this function
    int row = 0;
    int currBit = getBit(input, idx * 6 + 1);
    if (currBit == 1) {
        row += 2;
    }
    currBit = getBit(input, idx * 6 + 6);
    if (currBit == 1) {
        row += 1;
    }
    
    int column = 0;
    
    currBit = getBit(input, idx * 6 + 2);
    if (currBit == 1) {
        column += 8;
    }
    currBit = getBit(input, idx * 6 + 3);
    if (currBit == 1) {
        column += 4;
    }
    currBit = getBit(input, idx * 6 + 4);
    if (currBit == 1) {
        column += 2;
    }
    currBit = getBit(input, idx*6 + 5);
    if (currBit == 1) {
        column += 1;
    }
    
    byte tableVal[1] = {sBoxTable[idx][row][column]};
    
    for (int i = 1; i < 5; i++) {
        putBit(tableVal, i, getBit(tableVal, i + 4)); 
    }
    
    putBit(tableVal, 5, 0);
    putBit(tableVal, 6, 0);
    putBit(tableVal, 7, 0);
    putBit(tableVal, 8, 0);
    
    for (int i = 1; i < 9; i++){
        putBit(output, i, getBit(tableVal, i));
    }
}

/**
  Function that uses the given byte array R and the key K to compute the 
  f function, then stores it in the result array. 
  
  @param result result of calcultions
  @param R value to put through fFunction
  @param K subkey to assist fFunction
*/
void fFunction( byte result[ BLOCK_HALF_BYTES ], byte const R[ BLOCK_HALF_BYTES ], byte const K[ SUBKEY_BYTES ] ){
    
    byte expandedR[ SUBKEY_BYTES ];
    permute(expandedR, R, expandedRSelector, SUBKEY_BITS);

    for (int i = 1; i <= SUBKEY_BITS; i++) {
 
        int rBit = getBit(expandedR, i);
        int kBit = getBit(K, i);
        
        if (kBit != rBit) {
            putBit(expandedR, i, 1);
        } else {
            putBit(expandedR, i, 0);
        }
    }	
    
    
    byte temp[1];
    byte postS[ BLOCK_HALF_BYTES];
    int postSIndex = 0;
    
    for (int i = 0; i < SBOX_COUNT; i++) {
    
        sBox( temp, expandedR, i);
        
        for (int i = postSIndex + 1; i < postSIndex + SBOX_OUTPUT_BITS + 1; i++) {
            
            putBit(postS, i, getBit(temp, i - postSIndex));
            
        }
        
        postSIndex = postSIndex + SBOX_OUTPUT_BITS;
        
    }
    
    permute(result, postS, fFunctionPerm, BLOCK_HALF_BITS);
    
}

/**
  Function that encrpts the data within the given DESBlock, using the 2D array of 
  keys given to assist in encryption. The encrypted data is stored back in the block.
  
  @param block block to encrypt
  @param K keys to use to encrypt the block
*/
void encryptBlock( DESBlock *block, byte const K[ ROUND_COUNT ][ SUBKEY_BYTES ] ){
    
    byte R[ ROUND_COUNT ][ BLOCK_HALF_BYTES ];
    byte L[ ROUND_COUNT ][ BLOCK_HALF_BYTES ];
    
    //output, input, perm, n
    permute(R[0], block->data, rightInitialPerm, BLOCK_HALF_BITS);
    permute(L[0], block->data, leftInitialPerm, BLOCK_HALF_BITS);
    
    for (int i = 1; i < ROUND_COUNT; i++) {
    
        for (int j = 0; j < BLOCK_HALF_BYTES; j++) {
            L[i][j] = R[i - 1][j];
        }
        
        fFunction(R[i], R[i - 1], K[i]); 
        
        //XOR with L[i - 1];
        for (int j = 0; j < BLOCK_BYTES; j++) {
            byte a = R[i][j];
            byte b = L[i - 1][j];
            
            
            R[i][j] = a ^ b;
        }
    }
    
    byte temp[BLOCK_BYTES];
    
    //read R then L into temp
    for (int i = 0; i < BLOCK_HALF_BYTES; i++) {
        temp[i] = R[ROUND_COUNT - 1][i];
    }
    for (int i = BLOCK_HALF_BYTES; i < BLOCK_BYTES; i++) {
        temp[i] = L[ROUND_COUNT - 1][i - BLOCK_HALF_BYTES];
    }
    
    byte final[BLOCK_BYTES];
    
    permute(final, temp, finalPerm, BLOCK_BITS);
    
    for (int i = 0; i < BLOCK_BYTES; i++) {
        block->data[i] = final[i];
    }
    
    block->len = BLOCK_BYTES;
}

/**
  Function that decrypts the data within the given DESBlock, using the 2D array of 
  keys given to assist in decryption. The decrypted data is stored back in the block.
  
  @param block block to decrypt
  @param K keys to use to decrypt the block
*/
void decryptBlock( DESBlock *block, byte const K[ ROUND_COUNT ][ SUBKEY_BYTES ] ){
    
    byte R[ ROUND_COUNT ][ BLOCK_HALF_BYTES ];
    byte L[ ROUND_COUNT ][ BLOCK_HALF_BYTES ];
    
    //output, input, perm, n
    permute(R[0], block->data, rightInitialPerm, BLOCK_HALF_BITS);
    permute(L[0], block->data, leftInitialPerm, BLOCK_HALF_BITS);
    
    for (int i = 1; i < ROUND_COUNT; i++) {
        for (int j = 0; j < BLOCK_HALF_BYTES; j++) {
            L[i][j] = R[i - 1][j];
        }
        
        fFunction(R[i], R[i - 1], K[ROUND_COUNT - i]);
        
        //XOR with L[i - 1];
        for (int j = 0; j < BLOCK_BYTES; j++) {
            byte a = R[i][j];
            byte b = L[i - 1][j];
            
            
            R[i][j] = a ^ b;
        }
    }
    
    byte temp[BLOCK_BYTES];
    
    //read R then L into temp
    for (int i = 0; i < BLOCK_HALF_BYTES; i++) {
        
        temp[i] = R[ROUND_COUNT - 1][i];
    }
    for (int i = BLOCK_HALF_BYTES; i < BLOCK_BYTES; i++) {
        
        temp[i] = L[ROUND_COUNT - 1][i - BLOCK_HALF_BYTES];
    }
    
    byte final[BLOCK_BYTES];
    
    permute(final, temp, finalPerm, BLOCK_BITS);
    
    for (int i = 0; i < BLOCK_BYTES; i++) {
        block->data[i] = final[i];
        
        if (final[i] == 0x00) {
            block->len--;
        }
    }
}