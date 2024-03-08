package backend;

public class Airport {
    {
        private String location;
        private String code;

        public Airport(String location, String code) {
            this.location = location;
            this.code = code;
        }

        public String getLocation() {
            return this.location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
