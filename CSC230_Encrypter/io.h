/** 
    @file io.h
    @author Kal Corwin
    Header for io.c.
*/

#include <stdio.h>
#include "DES.h"

/** Function prototypes */
void readBlock( FILE *fp, DESBlock *block );
void writeBlock( FILE *fp, DESBlock const *block );