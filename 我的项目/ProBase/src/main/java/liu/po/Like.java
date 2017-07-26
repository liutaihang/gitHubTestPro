package liu.po;

public class Like {
		String type;
		String name;
		
		
		public Like() {
			super();
		}
		public Like(String type, String name) {
			super();
			this.type = type;
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "Like [type=" + type + ", name=" + name + "]";
		}
}
