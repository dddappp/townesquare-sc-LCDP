module townesquare_sc::post_update_logic {
    use std::string::String;
    use townesquare_sc::post;
    use townesquare_sc::post_event;

    friend townesquare_sc::post_aggregate;

    public(friend) fun verify(
        account: &signer,
        poster: address,
        user_id: String,
        content: String,
        digest: String,
        post: &post::Post,
    ): post::PostEvent {
        let _ = account;
        post::new_post_updated(
            post,
            poster,
            user_id,
            content,
            digest,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        post_updated: &post::PostEvent,
        post: post::Post,
    ): post::Post {
        let poster = post_event::poster(post_updated);
        let user_id = post_event::user_id(post_updated);
        let content = post_event::content(post_updated);
        let digest = post_event::digest(post_updated);
        let post_id = post::post_id(&post);
        let _ = post_id;
        post::set_poster(&mut post, poster);
        post::set_user_id(&mut post, user_id);
        post::set_content(&mut post, content);
        post::set_digest(&mut post, digest);
        post
    }

}
