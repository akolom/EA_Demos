
ROOT Application security...
NO Web support...Just using CORE Security.


ACL...
Dynamically generated for all new Comments...
There is an initializeACL in the  CommentServiceImpl  - invoked on save

ADDED Role Hierarchy
This means that ROLE_ADMIN has all the features of ROLE_USER etc. [ READ Access to ALL Comments]
Do NOT need to list both when PreAuthorize is used..