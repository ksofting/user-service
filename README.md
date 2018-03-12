Create a new repository

git clone http://192.168.0.251/ane/basic/user-service.git

cd user-service

touch README.md

git add README.md

git commit -m "add README"

git push -u origin master

-------------------------------------------------------------------------------

Existing folder

cd existing_folder

git init

git remote add origin http://192.168.0.251/ane/basic/user-service.git

git add .

git commit -m "Initial commit"

git push -u origin master

------------------------------------------------------------------------------

Existing Git repository

cd existing_repo

git remote add origin http://192.168.0.251/ane/basic/user-service.git

git push -u origin --all

git push -u origin --tags
