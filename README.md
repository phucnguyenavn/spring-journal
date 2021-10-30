## Learnt
- Relationship mappings make more sense now. 
- Select part of object other than the whole in JpaRepo.

## Goals
- An app where I can keep track of myself whether it is thought or activity, mood.

## TODOS
- ~~User System~~
- Journal
- Todo + Pomodoro

## Unknown problem
- Want to validate entity but could not, so choose @Valid controller.
- ~~Could not delete row base on multiple
value.~~ @Query("Delete ... exists (select ...)").

## Process
- Registration user 27/10
- Authentication filter 28/10
- Authorization filter 28/10
- Journal : Add, FindAll, FindOne 29/10, DeleteOne 30/10
