// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module townesquare_sc::post_event {

    use std::string::String;
    use townesquare_sc::post::{Self, PostEvent};

    public fun event_type(post_event: &PostEvent): u8 {
        post::post_event_event_type(post_event)
    }

    public fun post_id(post_event: &PostEvent): u128 {
        post::post_event_post_id(post_event)
    }

    public fun poster(post_event: &PostEvent): address {
        post::post_event_poster(post_event)
    }

    public fun user_id(post_event: &PostEvent): String {
        post::post_event_user_id(post_event)
    }

    public fun content(post_event: &PostEvent): String {
        post::post_event_content(post_event)
    }

    public fun digest(post_event: &PostEvent): String {
        post::post_event_digest(post_event)
    }

}
