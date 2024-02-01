/**
  @file map.c
  @author Kal Corwin
  Implementation for the map component, a hash map.
  */

#include "map.h"
#include <stdlib.h>
#include "value.h"

typedef struct MapPairStruct MapPair;

/** Key/Value pair to put in a hash map. */
struct MapPairStruct {
  /** Key part of this node, stored right in the node to improve locality. */
  Value key;
  
  /** Value part of this node, stored right in the node to improve locality. */
  Value val;
  
  /** Pointer to the next node at the same element of this table. */
  MapPair *next;
};

/** Representation of a hash table implementation of a map. */
struct MapStruct {
  /** Table of key / value pairs. */
  MapPair **table;

  /** Length of the table. */
  int tlen;
  
  /** Number of key / value pairs in the map. */
  int size;
};

/**
  Function that allocates a new map object and allocates the table stored in the map with the
  given length. 
  
  @param len length to initialize table
  @return the allocated map
*/
Map *makeMap( int len ){
  Map *m = (Map *)malloc(sizeof(Map));
  m->table = (MapPair **)malloc(len * sizeof(MapPair));
  
  for (int i = 0; i < len; i++){
      m->table[i] = NULL;
  }
  m->tlen = len;
  m->size = 0;
  
  return m;
}

/**
  This fucntion returns the current size of the given Map
  
  @param m Map with size to return
  @return size of the given Map
*/
int mapSize( Map *m ){
  return m->size;
}

/**
  This function sets the value of the MapPair within the given Map with the given key to the given val. If
  no MapPair with that key exists, the function uses the hash function of the given key to determine where 
  to add a new MapPair with the given key and val.
  
  @param m Map to set value in
  @param key key of MapPair with val to set
  @param val val to set MapPair's val to
*/
void mapSet( Map *m, Value *key, Value *val ){

  Value *temp = mapGet(m, key);

  //if an entry with that key already exists within the map
  if (temp != NULL){
    temp->empty(temp);
    //move from val to temp
    temp->move(val, temp);
    val->vptr = NULL;
    key->empty(key);
    return;
  }
  
  //look through innitial map list to see if theres already a sublist for the hashkey
  MapPair **curr = m->table;
  while (curr != NULL && *curr != NULL) {
    MapPair *currPair = *curr;
    if (currPair->key.hash(&(currPair->key)) % m->tlen == key->hash(key) % m->tlen) {
    
      MapPair *new = (MapPair *)malloc(sizeof(MapPair));
      new->val = *val;
      new->key = *key;
      new->next = NULL;

      while (currPair->next != NULL){
        currPair = currPair->next;
      }
      currPair->next = new;
      m->size++;
      //new MapPair takes ownership of key and val, so they don't need to be freed
      return;
    }
    //move one down the list
    curr++;
  }
  //add new mapPair to main list
  MapPair *new = (MapPair *)malloc(sizeof(MapPair));
  new->val = *val;
  new->key = *key;
  new->next = NULL;
  
  *curr = new;
  m->size++;
  
  //new MapPair takes ownership of key and val, so they don't need to be freed
  return;
}

/**
  Function that returns the val of the MapPair with the given key. If no MapPair with the given
  key exists within the given Map, the function returns NULL.
  
  @param m Map to search through
  @param key key to search for
  @return value associated with the given key
*/
Value *mapGet( Map *m, Value *key ){

  if (m->size == 0) {
    return NULL;
  }
  
  MapPair **curr = m->table;
  while ( curr != NULL && *curr != NULL ){
    MapPair *listCurr = *curr;
    
    while (listCurr != NULL) {

      if ( key->equals(key, &(listCurr->key)) ){
        return &(listCurr->val);
      }
    listCurr = listCurr->next;
    }
  curr++;
  }
  return NULL;
}

/**
  This function removes the MapPair with the given key from the given map, freeing all allocated memory. If the
  MapPair is successfully removed, the function returns true. If no MapPair with the given key exists, the function
  returns false.
  
  @param m map to remove MapPair from
  @param key key of MapPair to remove
  @return true if the MapPair is removed, false if it doesn't exist
*/
bool mapRemove( Map *m, Value *key ){

  if (m->size == 0){
    return false;
  }
  
  MapPair **curr = m->table;
  while ( curr != NULL && *curr != NULL){
    MapPair *listCurr = *curr;
    MapPair *prev = listCurr;
    
    while (listCurr != NULL) {

      if (key->equals(key, &(listCurr->key))){
 
        if (prev == listCurr && listCurr->next == NULL){
          //move remaining items up
          MapPair **temp = curr + 1;
          MapPair **tempPrev = curr;

          while (temp != NULL && *temp != NULL){
            
            *tempPrev = *temp;
            
            tempPrev++;
            temp++;
          }
  
          *tempPrev = NULL;

        } else if (prev == listCurr) {
          *curr = listCurr->next;
        } else {
          prev->next = listCurr->next;
        }
        listCurr->key.empty(&(listCurr->key));
        listCurr->val.empty(&(listCurr->val));

        free(listCurr);

        m->size--;
        return true;
      }
      prev = listCurr;
      listCurr = listCurr->next;
    }
    curr++;
  }
  return false;
}

/**
  This function frees all Values and MapPairs within the given Map, before freeing the Map's table
  and the Map itself.
  
  @param Map Map to free
*/
void freeMap( Map *m ){
  
  if (m->size == 0) {
    free(m->table);
    free(m);
    return;
  }

  MapPair **curr = m->table;
  while ( curr != NULL && *curr != NULL ){
    MapPair *listCurr = *curr;
    MapPair *temp = *curr;

    while (listCurr != NULL) {
      listCurr = listCurr->next;
      
      temp->key.empty(&(temp->key));
      temp->val.empty(&(temp->val));
      
      free(temp);
      temp = listCurr;
    }
    
    curr++;
  }
  free(m->table);
  free(m);
}