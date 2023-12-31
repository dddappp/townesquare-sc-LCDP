// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module townesquare_sc::townesquare_state {
    use aptos_framework::account;
    use aptos_framework::event;
    use townesquare_sc::genesis_account;
    use townesquare_sc::pass_object;
    friend townesquare_sc::townesquare_state_create_logic;
    friend townesquare_sc::townesquare_state_update_logic;
    friend townesquare_sc::townesquare_state_delete_logic;
    friend townesquare_sc::townesquare_state_aggregate;

    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const ENotInitialized: u64 = 110;

    struct Events has key {
        townesquare_state_created_handle: event::EventHandle<TownesquareStateCreated>,
        townesquare_state_updated_handle: event::EventHandle<TownesquareStateUpdated>,
        townesquare_state_deleted_handle: event::EventHandle<TownesquareStateDeleted>,
    }

    public fun initialize(account: &signer) {
        genesis_account::assert_genesis_account(account);

        let res_account = genesis_account::resource_account_signer();
        move_to(&res_account, Events {
            townesquare_state_created_handle: account::new_event_handle<TownesquareStateCreated>(&res_account),
            townesquare_state_updated_handle: account::new_event_handle<TownesquareStateUpdated>(&res_account),
            townesquare_state_deleted_handle: account::new_event_handle<TownesquareStateDeleted>(&res_account),
        });

    }

    struct TownesquareState has key, store {
        version: u64,
        is_emergency: bool,
        user_admin: address,
        post_admin: address,
    }

    public fun version(townesquare_state: &TownesquareState): u64 {
        townesquare_state.version
    }

    public fun is_emergency(townesquare_state: &TownesquareState): bool {
        townesquare_state.is_emergency
    }

    public(friend) fun set_is_emergency(townesquare_state: &mut TownesquareState, is_emergency: bool) {
        townesquare_state.is_emergency = is_emergency;
    }

    public fun user_admin(townesquare_state: &TownesquareState): address {
        townesquare_state.user_admin
    }

    public(friend) fun set_user_admin(townesquare_state: &mut TownesquareState, user_admin: address) {
        townesquare_state.user_admin = user_admin;
    }

    public fun post_admin(townesquare_state: &TownesquareState): address {
        townesquare_state.post_admin
    }

    public(friend) fun set_post_admin(townesquare_state: &mut TownesquareState, post_admin: address) {
        townesquare_state.post_admin = post_admin;
    }

    public(friend) fun new_townesquare_state(
        is_emergency: bool,
        user_admin: address,
        post_admin: address,
    ): TownesquareState {
        TownesquareState {
            version: 0,
            is_emergency,
            user_admin,
            post_admin,
        }
    }

    struct TownesquareStateCreated has store, drop {
        is_emergency: bool,
        user_admin: address,
        post_admin: address,
    }

    public fun townesquare_state_created_is_emergency(townesquare_state_created: &TownesquareStateCreated): bool {
        townesquare_state_created.is_emergency
    }

    public fun townesquare_state_created_user_admin(townesquare_state_created: &TownesquareStateCreated): address {
        townesquare_state_created.user_admin
    }

    public fun townesquare_state_created_post_admin(townesquare_state_created: &TownesquareStateCreated): address {
        townesquare_state_created.post_admin
    }

    public(friend) fun new_townesquare_state_created(
        is_emergency: bool,
        user_admin: address,
        post_admin: address,
    ): TownesquareStateCreated {
        TownesquareStateCreated {
            is_emergency,
            user_admin,
            post_admin,
        }
    }

    struct TownesquareStateUpdated has store, drop {
        version: u64,
        is_emergency: bool,
        user_admin: address,
        post_admin: address,
    }

    public fun townesquare_state_updated_is_emergency(townesquare_state_updated: &TownesquareStateUpdated): bool {
        townesquare_state_updated.is_emergency
    }

    public fun townesquare_state_updated_user_admin(townesquare_state_updated: &TownesquareStateUpdated): address {
        townesquare_state_updated.user_admin
    }

    public fun townesquare_state_updated_post_admin(townesquare_state_updated: &TownesquareStateUpdated): address {
        townesquare_state_updated.post_admin
    }

    public(friend) fun new_townesquare_state_updated(
        townesquare_state: &TownesquareState,
        is_emergency: bool,
        user_admin: address,
        post_admin: address,
    ): TownesquareStateUpdated {
        TownesquareStateUpdated {
            version: version(townesquare_state),
            is_emergency,
            user_admin,
            post_admin,
        }
    }

    struct TownesquareStateDeleted has store, drop {
        version: u64,
    }

    public(friend) fun new_townesquare_state_deleted(
        townesquare_state: &TownesquareState,
    ): TownesquareStateDeleted {
        TownesquareStateDeleted {
            version: version(townesquare_state),
        }
    }


    public(friend) fun update_version_and_add(townesquare_state: TownesquareState) {
        townesquare_state.version = townesquare_state.version + 1;
        //assert!(townesquare_state.version != 0, EInappropriateVersion);
        private_add_townesquare_state(townesquare_state);
    }

    public(friend) fun add_townesquare_state(townesquare_state: TownesquareState) {
        assert!(townesquare_state.version == 0, EInappropriateVersion);
        private_add_townesquare_state(townesquare_state);
    }

    public(friend) fun remove_townesquare_state(): TownesquareState acquires TownesquareState {
        assert!(exists<TownesquareState>(genesis_account::resource_account_address()), ENotInitialized);
        move_from<TownesquareState>(genesis_account::resource_account_address())
    }

    fun private_add_townesquare_state(townesquare_state: TownesquareState) {
        move_to(&genesis_account::resource_account_signer(), townesquare_state);
    }

    public fun get_townesquare_state(): pass_object::PassObject<TownesquareState> acquires TownesquareState {
        let townesquare_state = remove_townesquare_state();
        pass_object::new(townesquare_state)
    }

    public fun singleton_is_emergency(): bool acquires TownesquareState {
        let townesquare_state = borrow_global<TownesquareState>(genesis_account::resource_account_address());
        townesquare_state.is_emergency
    }

    public fun singleton_user_admin(): address acquires TownesquareState {
        let townesquare_state = borrow_global<TownesquareState>(genesis_account::resource_account_address());
        townesquare_state.user_admin
    }

    public fun singleton_post_admin(): address acquires TownesquareState {
        let townesquare_state = borrow_global<TownesquareState>(genesis_account::resource_account_address());
        townesquare_state.post_admin
    }

    public fun return_townesquare_state(townesquare_state_pass_obj: pass_object::PassObject<TownesquareState>) {
        let townesquare_state = pass_object::extract(townesquare_state_pass_obj);
        private_add_townesquare_state(townesquare_state);
    }

    public(friend) fun drop_townesquare_state(townesquare_state: TownesquareState) {
        let TownesquareState {
            version: _version,
            is_emergency: _is_emergency,
            user_admin: _user_admin,
            post_admin: _post_admin,
        } = townesquare_state;
    }

    public fun townesquare_state_exists(): bool {
        exists<TownesquareState>(genesis_account::resource_account_address())
    }

    public(friend) fun emit_townesquare_state_created(townesquare_state_created: TownesquareStateCreated) acquires Events {
        assert!(exists<Events>(genesis_account::resource_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resource_account_address());
        event::emit_event(&mut events.townesquare_state_created_handle, townesquare_state_created);
    }

    public(friend) fun emit_townesquare_state_updated(townesquare_state_updated: TownesquareStateUpdated) acquires Events {
        assert!(exists<Events>(genesis_account::resource_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resource_account_address());
        event::emit_event(&mut events.townesquare_state_updated_handle, townesquare_state_updated);
    }

    public(friend) fun emit_townesquare_state_deleted(townesquare_state_deleted: TownesquareStateDeleted) acquires Events {
        assert!(exists<Events>(genesis_account::resource_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resource_account_address());
        event::emit_event(&mut events.townesquare_state_deleted_handle, townesquare_state_deleted);
    }

}
