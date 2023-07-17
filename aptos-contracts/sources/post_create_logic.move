module townesquare_sc::post_create_logic {
    use std::string::String;
    use townesquare_sc::post;
    use townesquare_sc::post_created;

    friend townesquare_sc::post_aggregate;

    public(friend) fun verify(
        account: &signer,
        poster: address,
        user_id: String,
        content: String,
        digest: String,
    ): post::PostCreated {
        let _ = account;
        post::new_post_created(
            poster,
            user_id,
            content,
            digest,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        post_created: &post::PostCreated,
    ): post::Post {
        let poster = post_created::poster(post_created);
        let user_id = post_created::user_id(post_created);
        let content = post_created::content(post_created);
        let digest = post_created::digest(post_created);
        post::create_post(
            poster,
            user_id,
            content,
            digest,
        )
    }

}
