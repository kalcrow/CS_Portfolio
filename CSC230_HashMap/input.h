/**
  @file input.h
  @author Kal Corwin(ercorwin)
  Header file associated with input.c
 */

#include <stdbool.h>
#include <stdio.h>

/**
  This method reads a single line of input from the given input stream, either 
  stdin or a file, storing it in a dynamically allocated character array (string) 
  that resizes as necessary.

  @param fp filestream to read from
  @return line of input as a string
*/
char *readLine(FILE *fp);
