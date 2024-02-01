/**
  @file input.c
  @author Kal Corwin (ercorwin)
  This program contains a helper method to assist other programs with reading
  a line of input into a string. 
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "input.h"

/** Initial capacity of string that information is read into */
#define INITIAL_CAP 5
/** Value used to increase the capacity of stringIn array */
#define CAP_DOUBLE 2

char *readLine(FILE *fp) {
    char *stringIn = (char *)malloc(sizeof(char) * (INITIAL_CAP + 1));
    char tempChar;
    int size = 0;
    int capacity = INITIAL_CAP;
    
    while (fscanf(fp, "%c", &tempChar) == 1 && tempChar != '\n') {
        
        //resize array if it has reached capacity
        //moved to top of while loop so that this only happens if there is another
       //    character to add - won't resize if size == capacity AND it has reached 
        //    end of line
        if (size == capacity) {
            capacity *= CAP_DOUBLE;
            stringIn = (char *)realloc(stringIn, sizeof(char) * (capacity + 1));
        }
        
        //add character to string
        stringIn[size] = tempChar;
        //add end character to string
        stringIn[size + 1] = '\0';
        
        size++;
    }
    
    //case if there is no more input to read
    if (size == 0) {
        //free space and return NULL
        free(stringIn);
        return NULL;
    }
    
    //return array
    return stringIn;
}