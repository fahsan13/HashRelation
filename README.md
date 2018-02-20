## HashRelation

Implementing a Relation [abstract data type](https://en.wikipedia.org/wiki/Abstract_data_type) (ADT) using a (closed bucket) [Hash Table](https://en.wikipedia.org/wiki/Hash_table) data structure.

### About

A Relation is essentially a generalization of a [Map](https://en.wikipedia.org/wiki/Associative_array). Whereas a Map demands that each key is unique, a Relation allows for duplicate keys with different values and instead tests that a given key/value pair is unique. It's similar to a [MultiMap](https://en.wikipedia.org/wiki/Multimap) but only allows for a single instances of a key/value pair.

This repo contains an interface, `Relation.java`, which declares the methods that characterise a Relation.  This interface acts as a contract for implementing classes and uses [generic typing](https://en.wikipedia.org/wiki/Generic_programming).

`HashRelation.java` is the class implementing `Relation.java` with a Hash Table. It relies on an private static inner class, `Node`, to generate buckets.

### To-do

* Example class with main method  
* J-Unit test cases <br>
 
