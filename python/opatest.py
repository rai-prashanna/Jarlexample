# from opa_wasm import OPAPolicy
#
# # Load a policy by specifying its file path
# policy = OPAPolicy('/repo/policy2/policy.wasm')
#
# # Optional: Set policy data
# #policy.set_data({"message": "world"})
#
# # Evaluate the policy
# input = {"message": "world"}
# result = policy.evaluate(input)
# print(" the result of opa  ")
# print(result)

import pip
installed_packages = pip.get_installed_distributions()
installed_packages_list = sorted(["%s==%s" % (i.key, i.version)
     for i in installed_packages])
print(installed_packages_list)