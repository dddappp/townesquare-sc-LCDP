# Aptos Move CLI Cheatsheet

[ToC]

## Post aggregate

### Create method

```shell
aptos move run --function-id 'default::post_aggregate::create' \
--args address:poster 'string:user_id' 'string:content' 'string:digest' \
--assume-yes
```

### Update method

```shell
aptos move run --function-id 'default::post_aggregate::update' \
--args u128:post_id address:poster 'string:user_id' 'string:content' 'string:digest' \
--assume-yes
```

### Delete method

```shell
aptos move run --function-id 'default::post_aggregate::delete' \
--args u128:post_id \
--assume-yes
```

## User aggregate

### Create method

```shell
aptos move run --function-id 'default::user_aggregate::create' \
--args address:user_wallet 'string:username' 'string:profile_image' 'string:bio' \
--assume-yes
```

### Update method

```shell
aptos move run --function-id 'default::user_aggregate::update' \
--args address:user_wallet 'string:username' 'string:profile_image' 'string:bio' \
--assume-yes
```

### Delete method

```shell
aptos move run --function-id 'default::user_aggregate::delete' \
--args address:user_wallet \
--assume-yes
```

## TownesquareState singleton object

### Create method

```shell
aptos move run --function-id 'default::townesquare_state_aggregate::create' \
--args bool:is_emergency address:user_admin address:post_admin \
--assume-yes
```

### Update method

```shell
aptos move run --function-id 'default::townesquare_state_aggregate::update' \
--args bool:is_emergency address:user_admin address:post_admin \
--assume-yes
```

### Delete method

```shell
aptos move run --function-id 'default::townesquare_state_aggregate::delete' \
--assume-yes
```

