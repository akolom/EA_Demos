
Exercises ONe -To One unidirectional
Cascade Persist the Credentials
LAZY load the UserCredentials on FETCH

Demonstrate this by having TWO findOne services

findOne is the regular Entity Manager find will cause Lazy load 
if credentials are referenced

findOneFull   "hydrates" the UserCredentials

ALSO demonstrate Dynamic Criteria API query [ Lesson 4]

ALSO demonstrate Cascade Delete
Deleting member cascades to UserCredentials BUT does NOT cascade to Authority
Authority FK to UserCredentials is cleared