SUB_PROJECT = chipyard

base_dir=$(abspath ..)
sim_dir=$(abspath .)

include $(base_dir)/variables.mk

# need sim_name to generate EICG_wrapper.v
sim_name = vcs

# import other necessary rules and variables
include $(base_dir)/common.mk

.PHONY: default rocket boom
default: rocket

rocket: CONFIG = JtagRocketConfig
rocket: $(sim_vsrcs)
	mv $(gen_dir)/$(long_name) .

boom: CONFIG = JtagLargeBoomConfig
boom: $(sim_vsrcs)
	mv $(gen_dir)/$(long_name) .

ariane: CONFIG = JtagArianeConfig
ariane: $(sim_vsrcs)
	mv $(gen_dir)/$(long_name) .

.PHONY: clean
clean:
	rm -rf $(gen_dir)
