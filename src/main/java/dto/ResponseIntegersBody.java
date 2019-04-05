package dto;

public class ResponseIntegersBody {
    private int id;
    private String jsonrpc;
    private Result result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        private int bitsUsed;
        private int bitsLeft;
        private int requestsLeft;
        private int advisoryDelay;
        private Random random;

        public int getBitsUsed() {
            return bitsUsed;
        }

        public void setBitsUsed(int bitsUsed) {
            this.bitsUsed = bitsUsed;
        }

        public int getBitsLeft() {
            return bitsLeft;
        }

        public void setBitsLeft(int bitsLeft) {
            this.bitsLeft = bitsLeft;
        }

        public int getRequestsLeft() {
            return requestsLeft;
        }

        public void setRequestsLeft(int requestsLeft) {
            this.requestsLeft = requestsLeft;
        }

        public int getAdvisoryDelay() {
            return advisoryDelay;
        }

        public void setAdvisoryDelay(int advisoryDelay) {
            this.advisoryDelay = advisoryDelay;
        }

        public Random getRandom() {
            return random;
        }

        public void setRandom(Random random) {
            this.random = random;
        }

        public static class Random {
            private int[] data;
            private String completionTime;

            public int[] getData() {
                return data;
            }

            public void setData(int[] data) {
                this.data = data;
            }

            public String getCompletionTime() {
                return completionTime;
            }

            public void setCompletionTime(String completionTime) {
                this.completionTime = completionTime;
            }
        }
    }
}