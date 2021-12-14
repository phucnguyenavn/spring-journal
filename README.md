## Learnt
- Relationship mappings make more sense now. 
- Select part of object other than the whole in JpaRepo.
- Turn out doesn't need Base64 <- ->String emoji... varchar191
- Object as param jpa


## Goals
- An app where I can keep track of myself whether it is thought or activity, mood.

## TODOS
- ~~User System~~
- ~~Journal~~


## Unknown problem
- Want to validate entity but could not, so choose @Valid controller.
- ~~Could not delete row base on multiple
value.~~ @Query("Delete ... exists (select ...)").
- ~~Could not update journal~~. Somehow I update an old one not with the new one

## Process
- ~~Registration user~~ 
- ~~Authentication filter~~ 
- ~~Authorization filter~~
- ~~Refactor Journal.~~
- ~~Sync journal to frontend~~
