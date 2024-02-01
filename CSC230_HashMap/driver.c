/**
  @file driver.cabs
  @author Kal Corwin
  Program to take user input and update a map accordingly
*/

#include "input.h"
#include "map.h"
#include "value.h"
#include <string.h>
#include <stdlib.h>

/** Length of map used to execute driver functions */
#define MAP_LEN 100
/** Max length of argument arrays */
#define MAX_ARG_LEN 50

/**
  This method takes arguments from standard input and interprets them to manipulate
  a map. If the command is invalid for any reason, the method prints "Invalid command"
  and requests another command from the user.
  
  @param argc number of arguments
  @param argv array of string arguments
  @return 0 if the method runs succesfully
*/
int main( int argc, char *argv[] ){
    Map *m = makeMap(MAP_LEN);
    
    printf("%s", "cmd> ");
    char *lineIn = readLine(stdin); //may need to edit readline to take no input then use scanf();
    
    
    while (lineIn != NULL) {
        //process line of input
        printf("%s\n", lineIn); //this is working
        char cmd[MAX_ARG_LEN];
        int idx;
        sscanf(lineIn, "%s%n", cmd, &idx);

        if (cmd == NULL) {
            printf("%s\n", "Invalid command");
        } else {

            //SET command
            if (strcmp(cmd, "set") == 0){
                
                char paramOne[MAX_ARG_LEN];
                int newIdx;
                
                if (sscanf(lineIn + idx, "%s%n", paramOne, &newIdx) == 1) {
                    char paramTwo[MAX_ARG_LEN];
                    idx += newIdx;
                    
                    if (sscanf(lineIn + idx, "%s%n", paramTwo, &newIdx) == 1) {
                        char temp[MAX_ARG_LEN];
                        idx += newIdx;
                        
                        if (sscanf(lineIn + idx, "%s", temp) != 1) {

                            Value *key = (Value *)malloc(sizeof(Value));
                            Value *val = (Value *)malloc(sizeof(Value));

                            if (parseString(key, paramOne) != 0 ){

                                if (parseString(val, paramTwo) != 0) {
                                    mapSet(m, key, val);
                                    free(val);
                                    free(key);
                                } else {
                                    val->empty(val);

                                    if (parseInteger(val, paramTwo) != 0) {
                                        mapSet(m, key, val);
                                        free(val);
                                        free(key);
                                    } else {
                                        //INVALID COMMAND
                                        key->empty(key);
                                        free(key);
                                        val->empty(val);
                                        free(val);
                                        printf("%s\n", "Invalid command");
                                    }
                                }
                            } else {
                                key->empty(key);

                                if (parseInteger(key, paramOne) != 0) {
                                
                                    if (parseString(val, paramTwo) != 0) {

                                        mapSet(m, key, val);
                                        free(val);
                                        free(key);
                                    } else {
                                        val->empty(val);

                                        if (parseInteger(val, paramTwo) != 0) {
                                            mapSet(m, key, val);
                                            free(val);
                                            free(key);
                                        } else {
                                            //INVALID COMMAND
                                            key->empty(key);
                                            free(key);
                                            val->empty(val);
                                            free(val);
                                            printf("%s\n", "Invalid command");
                                        }
                                    }
                                } else {
                                    //INVALID COMMAND
                                    key->empty(key);
                                    free(key);
                                    printf("%s\n", "Invalid command");
                                }
                            }
                        } else {
                            //INVALID COMMAND
                            printf("%s\n", "Invalid command");
                        }
                    } else {
                        //INVALID COMMAND
                        printf("%s\n", "Invalid command");
                    }
                } else {
                    //INVALID COMMAND
                    printf("%s\n", "Invalid command");
                }
              //GET command
            } else if (strcmp(cmd, "get") == 0){
                
                char paramOne[MAX_ARG_LEN];
                int newIdx;

                if (sscanf(lineIn + idx, "%s%n", paramOne, &newIdx) == 1) {
                    char temp[MAX_ARG_LEN];
                    idx += newIdx;
                    
                    if (sscanf(lineIn + idx, "%s", temp) != 1) {

                        Value *key = (Value *)malloc(sizeof(Value));
                        
                        if (parseString(key, paramOne) != 0) {

                            Value *val = mapGet(m, key);
                            if (val == NULL){
                                key->empty(key);
                                free(key);
                                printf("%s\n", "Undefined");
                            } else {
                                val->print(val);
                                key->empty(key);
                                free(key);
                                printf("%s\n", "");
                            }
                        } else {
                            key->empty(key);

                            if (parseInteger(key, paramOne) != 0) {

                                Value *val = mapGet(m, key);
                                if (val == NULL){
                                    key->empty(key);
                                    free(key);
                                    printf("%s\n", "Undefined");
                                } else {
                                    val->print(val);
                                    key->empty(key);
                                    free(key);
                                    printf("%s\n", "");
                                }
                            } else {
                                //INVALID COMMAND
                                key->empty(key);
                                free(key);
                                printf("%s\n", "Invalid command");
                            }
                        }
                    } else {
                        //INVALID COMMAND
                        printf("%s\n", "Invalid command");
                    }
                } else {
                    //INVALID COMMAND
                    printf("%s\n", "Invalid command");
                }

              //REMOVE command
            } else if (strcmp(cmd, "remove")  == 0){

                char paramOne[MAX_ARG_LEN];
                int newIdx;
                
                if (sscanf(lineIn + idx, "%s%n", paramOne, &newIdx) == 1) {
                    char temp[MAX_ARG_LEN];
                    idx += newIdx;

                    if (sscanf(lineIn + idx, "%s", temp) != 1) {
                        Value *key = (Value *)malloc(sizeof(Value));

                        if (parseString(key, paramOne) != 0) {
                            bool remove = mapRemove(m, key);

                            key->empty(key);
                            free(key);

                            if (remove == false){
                                printf("%s\n", "Not in map");
                            }
                        } else {
                            key->empty(key);

                            if (parseInteger(key, paramOne) != 0) {
                                bool remove = mapRemove(m, key);
                                key->empty(key);
                                free(key);
                                if (remove == false){
                                    printf("%s\n", "Not in map");
                                }
                            } else {
                                key->empty(key);
                                free(key);
                                printf("%s\n", "Invalid command");
                            }
                        }
                    } else {
                        //INVALID COMMAND
                        printf("%s\n", "Invalid command");
                    }
                } else {
                    //INVALID COMMAND
                    printf("%s\n", "Invalid command");
                }
            } else if (strcmp(cmd, "size") == 0){
                char temp[MAX_ARG_LEN];

                if (sscanf(lineIn + idx, "%s", temp) != 1) {

                    //PERFORM SIZE COMMAND
                    printf("%d\n", mapSize(m));

                } else {
                    //INVALID COMMAND
                    printf("%s\n", "Invalid command");
                }
            } else if (strcmp(cmd, "quit") == 0) {
                char temp[MAX_ARG_LEN];

                if (sscanf(lineIn + idx, "%s", temp) != 1) {
                    free(lineIn);
                    freeMap(m);
                    return 0;
                } else {
                    //INVALID COMMAND
                    printf("%s\n", "Invalid command");
                }
            } else {
                //INVALID COMMAND
                printf("%s\n", "Invalid command");
            }
        }
        printf("\n");
        printf("cmd> ");

        free(lineIn);
        lineIn = readLine(stdin);

    }
    free(lineIn); //lineIn is malloced in input.c
    freeMap(m);
    return 0;
}