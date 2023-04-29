package policy

import future.keywords.if
import future.keywords.in

default allow := false

# METADATA
# entrypoint: true
allow = input.user.roles if "admin" in input.user.roles