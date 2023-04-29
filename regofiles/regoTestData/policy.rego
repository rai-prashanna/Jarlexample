package policy

import future.keywords.if
import future.keywords.in

default allow := false

# METADATA
# entrypoint: true
allow if "admin" in input.user.roles
