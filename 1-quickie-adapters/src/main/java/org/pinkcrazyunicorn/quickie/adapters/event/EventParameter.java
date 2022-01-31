package org.pinkcrazyunicorn.quickie.adapters.event;

public class EventParameter {
    public static EventParameter Profile = new ProfileParameter();
    public static EventParameter Food = new FoodParameter();
    public static EventParameter Opinion = new OpinionParameter();

    private final String name;
    private final String description;

    private EventParameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    private static class ProfileParameter extends EventParameter {
        protected ProfileParameter() {
            super("profile-name", "Profile to be used");
        }
    }

    private static class FoodParameter extends EventParameter {
        protected FoodParameter() {
            super("food", "Food to be used");
        }
    }

    private static class OpinionParameter extends EventParameter {
        protected OpinionParameter() {
            super("opinion", "Opinion to be used");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventParameter that = (EventParameter) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
