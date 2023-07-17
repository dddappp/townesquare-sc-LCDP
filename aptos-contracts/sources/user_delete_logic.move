module townesquare_sc::user_delete_logic {
    use townesquare_sc::user;

    friend townesquare_sc::user_aggregate;

    public(friend) fun verify(
        account: &signer,
        user: &user::User,
    ): user::UserDeleted {
        let _ = account;
        user::new_user_deleted(
            user,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        user_deleted: &user::UserDeleted,
        user: user::User,
    ): user::User {
        let user_wallet = user::user_wallet(&user);
        let _ = user_wallet;
        let _ = user_deleted;
        user
    }

}
