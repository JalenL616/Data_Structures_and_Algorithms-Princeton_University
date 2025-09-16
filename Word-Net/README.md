# Word Net

This project, completed for my high school data structures and algorithms course, is an implementation of a WordNet graph processing tool. The programming project is from Princeton University's "Algorithms, Part 2" course on Coursera.
The program reads the WordNet database files, builds a directed graph representing the semantic relationships, and then implements an algorithm to find the shortest ancestral path (SAP) between any two nouns. This functionality is then used in a client program to identify the "outcast" from a list of nouns.

https://www.coursera.org/learn/algorithms-part2/home/module/3

**Project Overview**

WordNet is a large lexical database of English where nouns, verbs, adjectives, and adverbs are grouped into sets of cognitive synonyms called synsets. Each synset represents a distinct concept. These synsets are interlinked by means of conceptual-semantic and lexical relations. This project focuses on the hypernym relationship, where one synset (the hyponym) is a subtype of another (the hypernym). For example, "maple" is a hyponym of "tree," and "tree" is a hypernym of "maple."
This network of synsets and hypernyms forms a directed acyclic graph (DAG). This project involves:
Parsing the WordNet data files to build this graph representation.
Implementing an efficient algorithm to find the shortest ancestral path between two synsets.
Creating a client program to apply this logic to solve a word puzzle.

**The WordNet Problem**

The core computational tasks are:
Distance: Find the length of the shortest path connecting two nouns in the WordNet graph.
Ancestor: Find the common ancestor in the shortest path between two nouns.
Outcast: Given a list of nouns, determine which one is semantically the "least related" to the others by calculating the sum of its distances to all other nouns in the list.
Core Components

**WordNet.java**

This class serves as the main interface to the WordNet database.
Constructor: Takes the filenames for the synsets and hypernyms files as arguments. It parses these files to build the underlying graph and necessary symbol tables for efficient lookup.
Data Structures:
**A Digraph** to store the hypernym relationships.
**Two HashMaps for fast lookups**: one to map nouns (Strings) to the set of synset IDs they belong to, and another to map synset IDs (Integers) back to the synset's information.
API: Provides methods like nouns(), isNoun(String word), distance(String nounA, String nounB), and sap(String nounA, String nounB). It acts as a wrapper, delegating the core distance and ancestor calculations to the SAP class.

**SAP.java (Shortest Ancestral Path)**

This is the algorithmic heart of the project. It operates on a Digraph (not necessarily a DAG) and finds the shortest path between two vertices or two sets of vertices.
Algorithm: The length() and ancestor() **methods are implemented using a simultaneous Breadth-First Search (BFS)**. The algorithm starts a BFS from both source vertices (or sets of vertices) in lockstep. The first time the two searches collide on a common vertex, that vertex is a candidate for the shortest common ancestor. The algorithm continues exploring until it can guarantee that no shorter path exists.

**Outcast.java**

This class is a client program that uses the WordNet and SAP objects to solve the "outcast" problem.
Functionality: Given a set of nouns, the outcast is the noun whose sum of distances to all other nouns in the set is the largest.
Algorithm: For each noun in the input set, it calculates the sum of the distances from it to every other noun in the set using the WordNet.distance() method. The noun with the maximum sum is the outcast.
Algorithm Details
The Shortest Ancestral Path is found by running a Breadth-First Search from each of the two source synsets simultaneously. We keep track of the distance to each vertex from both sources. When we find a vertex that has been visited by both BFS searches, we have found a common ancestor. We keep track of the ancestor that minimizes the sum of distances from the two sources and return that as our shortest ancestral path.

**Dependencies**

This project requires the algs4.jar library from the Princeton Algorithms, Part 2 course. This library provides essential classes for the project, including Digraph, BreadthFirstDirectedPaths, In, and StdOut.
