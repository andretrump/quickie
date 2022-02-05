package org.pinkcrazyunicorn.quickie.domain.profile;

abstract public class Opinion {
    public static final Opinion Foodgasm = new FoodgasmType();
    public static final Opinion Love = new LoveType();
    public static final Opinion Like = new LikeType();
    public static final Opinion Indifferent = new IndifferentType();
    public static final Opinion Dislike = new DislikeType();
    public static final Opinion Hate = new HateType();
    public static final Opinion Dealbreaker = new DealbreakerType();

    private String name;

    public String getName() {
        return name;
    }

    public Opinion(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Opinion opinion = (Opinion) o;

        return name.equals(opinion.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public abstract double getWeight();

    private static class FoodgasmType extends Opinion {
        private FoodgasmType() {
            super("Foodgasm");
        }

        @Override
        public double getWeight() {
            return 3;
        }
    }

    private static class LoveType extends Opinion {
        private LoveType() {
            super("Love");
        }

        @Override
        public double getWeight() {
            return 2;
        }
    }

    private static class LikeType extends Opinion {
        private LikeType() {
            super("Like");
        }

        @Override
        public double getWeight() {
            return 1;
        }
    }

    private static class IndifferentType extends Opinion {
        private IndifferentType() {
            super("Indifferent");
        }

        @Override
        public double getWeight() {
            return 0;
        }
    }

    private static class DislikeType extends Opinion {
        private DislikeType() {
            super("Dislike");
        }

        @Override
        public double getWeight() {
            return -1;
        }
    }

    private static class HateType extends Opinion {
        private HateType() {
            super("Hate");
        }

        @Override
        public double getWeight() {
            return -2;
        }
    }

    private static class DealbreakerType extends Opinion {
        private DealbreakerType() {
            super("Dealbreaker");
        }

        @Override
        public double getWeight() {
            return Double.NEGATIVE_INFINITY;
        }
    }
}
