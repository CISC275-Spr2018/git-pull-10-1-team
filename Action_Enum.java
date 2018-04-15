
public enum Action_Enum {
		FORWARD("orc_forward_"),
		DIE("orc_die_"),
		FIRE("orc_fire_"),
		JUMP("orc_jump_");
		
		private String name = null;
		
		private Action_Enum(String s){
			name = s;
		}
		public String getName() {
			return name;
		}

}
