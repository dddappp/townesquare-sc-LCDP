module townesquare_sc::post_delete_logic {
    use townesquare_sc::post;

    friend townesquare_sc::post_aggregate;

    public(friend) fun verify(
        account: &signer,
        post: &post::Post,
    ): post::PostDeleted {
        let _ = account;
        post::new_post_deleted(
            post,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        post_deleted: &post::PostDeleted,
        post: post::Post,
    ): post::Post {
        let post_id = post::post_id(&post);
        let _ = post_id;
        let _ = post_deleted;
        post
    }

}
