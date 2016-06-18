
ROOT Application security...
NO Web support...Just using CORE Security.

CUSTOM Authentication Manager

Create Sean -- ROLE_USER
Create Bill -- ROLE_ADMIN
Create Pete -- Role_USER

Member FindAll has Authorization ONLY ROLE_ADMIN which is Bill
 
code FIRST tries to access WITHOUT logging in -- ANONYMOUS User - Access Denied...

Login ... with a user [Sean,Bill or Pete] ONLY Bill has access

