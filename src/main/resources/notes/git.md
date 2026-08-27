### GIT 

---

1. TO CREATE SSH KEY FOR GIT
    **NOTE: REMOTE SHOULD SAY git@github.com:<USERNAME>/<REP>.git NOT https://github.com/<USERNAME>/<REP>.git**

   a. `ssh-keygen -t ed25519 -C "<EMAIL>"`

   b. Add ssh key to github

   c. `ssh -T git@github.com`

   d. `git remote set-url origin git@github.com:<USERNAME>/<REPO_NAME>.git`
3. SET USERNAME AND EMAIL GLOBALLY

   a. `git config --global user.name "<USERNAME>"`

   b. `git config --global user.email "<EMAIL>"`
5. VERIFY USERNAME AND EMAIL

   a. `git config user.name`

   b. `git config user.email`
7. TO CHANGE USERNAME AND EMAIL IN COMMIT (ALSO EASIER WITH A GIT LIBRARY)
    ```
    git filter-branch --env-filter '
    OLD_EMAIL="<WRONG_EMAIL>"
    CORRECT_NAME="<CORRECT_USERNAME>"
    CORRECT_EMAIL="<CORRECT_EMAIL>"
    if < "$GIT_AUTHOR_EMAIL" = "$OLD_EMAIL" >; then
        export GIT_AUTHOR_NAME="$CORRECT_NAME"
        export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
    fi

    if < "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" >; then
        export GIT_COMMITTER_NAME="$CORRECT_NAME"
        export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
    fi
    ' -- --all
    ```