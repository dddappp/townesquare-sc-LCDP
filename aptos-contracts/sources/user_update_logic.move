module townesquare_sc::user_update_logic {
    use std::string::String;
    use townesquare_sc::user;
    use townesquare_sc::user_updated;

    friend townesquare_sc::user_aggregate;

    public(friend) fun verify(
        account: &signer,
        username: String,
        profile_image: String,
        bio: String,
        user: &user::User,
    ): user::UserUpdated {
        let _ = account;
        user::new_user_updated(
            user,
            username,
            profile_image,
            bio,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        user_updated: &user::UserUpdated,
        user: user::User,
    ): user::User {
        let username = user_updated::username(user_updated);
        let profile_image = user_updated::profile_image(user_updated);
        let bio = user_updated::bio(user_updated);
        let user_wallet = user::user_wallet(&user);
        let _ = user_wallet;
        user::set_username(&mut user, username);
        user::set_profile_image(&mut user, profile_image);
        user::set_bio(&mut user, bio);
        user
    }

}
