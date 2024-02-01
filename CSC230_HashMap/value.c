/**
  @file value.c
  @author Kal Corwin
  Implementation for the value component, with support for integer
  and string values.
  */

#include "value.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

/** Values use for hash function */
#define HASH_ONE 10
#define HASH_TWO 6
#define HASH_THREE 3
#define HASH_FOUR 11
#define HASH_FIVE 15

//////////////////////////////////////////////////////////
// Integer implementation.

// print method for Integer.
static void printInteger( Value const *v )
{
  // Print the integer inside this value.
  printf( "%d", v->ival );
}

// move method for Integer.
static void moveInteger( Value const *src, Value *dest )
{
  // We just need to copy the value.
  dest->ival = src->ival;
  
  dest->print = src->print;
  dest->move = src->move;
  dest->equals = src->equals;
  dest->hash = src->hash;
  dest->empty = src->empty;
}

// equals method for Integer.
static bool equalsInteger( Value const *v, Value const *other )
{
  // Make sure the other object is also an Integer.
  // (i.e., it uses the same print funtion)
  if ( other->print != printInteger )
    return false;

  return v->ival == other->ival;
}

// hash method for Integer.
static unsigned int hashInteger( Value const *v )
{
  // This will overflow negative values to large positive ones.
  return v->ival;
}

// Free memory used inside this integer Value.
static void emptyInteger( Value *v )
{
  // An int vaue doesn't need any additional memory.
}

int parseInteger( Value *v, char const *str )
{
  // Try to parse an integer from str.
  int val, len;
  if ( sscanf( str, "%d%n", &val, &len ) != 1 )
    return 0;

  // Fill in all the fields of v for an integer type of value.
  v->print = printInteger;
  v->move = moveInteger;
  v->equals = equalsInteger;
  v->hash = hashInteger;
  v->empty = emptyInteger;
  v->ival = val;

  // Return how much of str we parsed.
  return len;
}

//////////////////////////////////////////////////////////
// String implementation.

static void printString( Value const *v )
{
  // Print the String inside this value.
  printf("%s", (char *)v->vptr);
}

// move method for String.
static void moveString( Value const *src, Value *dest )
{
  // We just need to copy the value.
  dest->vptr = src->vptr;
  
  dest->print = src->print;
  dest->move = src->move;
  dest->equals = src->equals;
  dest->hash = src->hash;
  dest->empty = src->empty;
}

// equals method for String.
static bool equalsString( Value const *v, Value const *other )
{
  // Make sure the other object is also a String.
  // (i.e., it uses the same print funtion)
  if ( other->print != printString )
    return false;

  return !strcmp(v->vptr, other->vptr);
}

// hash method for String.
static unsigned int hashString( Value const *v )
{
  size_t i = 1;
  unsigned int hash = 0;
  
  while (i != strlen((char *)v->vptr) - 1){
      hash += ((char *)v->vptr)[i++];
      hash += hash << HASH_ONE;
      hash ^= hash >> HASH_TWO;
  }
  
  hash += hash << HASH_THREE;
  hash ^= hash >> HASH_FOUR;
  hash += hash << HASH_FIVE;
  return hash;
}

// Free memory used inside this String Value.
static void emptyString( Value *v )
{
  free(v->vptr);
  v->vptr = NULL;
}

int parseString( Value *v, char const *str )
{

  v->print = printString;
  v->move = moveString;
  v->equals = equalsString;
  v->hash = hashString;
  v->empty = emptyString;
  v->vptr = malloc((strlen(str) + 1) * sizeof(char));
  
  int count = 0;
  int place = 0;
  while (*str != '"' && *str != '\0'){
      str++;
      count++;
  }
  
  if (*str == '\0'){
      return 0;
  }
  
  
  *((char *)v->vptr + place) = *str;
  str++;
  count++;
  place++;
  while (*str != '"' && *str != '\0'){
    if (*str == '\\'){
      str++;

      if (*str == '\"') {
        *((char *)v->vptr + place) = '\"';
        str++;
        place++;
        count++;
      } else if (*str == '\\'){
        *((char *)v->vptr + place) = '\\';
        str++;
        place++;
        count++;
      } else if (*str == 'n'){
        *((char *)v->vptr + place) = '\n';
        str++;
        place++;
        count++;
      } else if (*str == 't') {
        *((char *)v->vptr + place) = '\t';
        str++;
        place++;
        count++;
      }
    } else { 
      *((char *)v->vptr + place) = *str;
      str++;
      count++;
      place++;
    }
  }
  
  if (*str == '\0'){
    return 0;
  }
  
  *((char *)v->vptr + place) = *str;
  count++;
  place++;
  
  *((char *)v->vptr + place) = '\0';
  // Return how much of str we parsed.
  return count;
}