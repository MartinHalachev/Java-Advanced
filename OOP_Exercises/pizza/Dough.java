package pizza;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        ValidatorUtil.checkDoughWeight(weight);
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);

    }

    private void setWeight(double weight) {
        this.weight = weight;
    }

    private void setFlourType(String flourType) {
        ValidatorUtil.checkFlourTypeAndTechnique(flourType);
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        ValidatorUtil.checkFlourTypeAndTechnique(bakingTechnique);
        this.bakingTechnique = bakingTechnique;
    }

    public double calculateCalories() {
        double calories = this.weight * 2;
        switch (this.flourType) {
            case "White":
                calories *= Doughs.WHITE.getModifier();
                break;
            case "Wholegrain":
                calories *= Doughs.WHOLEGRAIN.getModifier();
                break;
        }
        switch (this.bakingTechnique) {
            case "Crispy":
                calories *= Techniques.CRISPY.getModifier();
                break;
            case "Chewy":
                calories *= Techniques.CHEWY.getModifier();
                break;
            case "Homemade":
                calories *= Techniques.HOMEMADE.getModifier();
                break;
        }
        return calories;
    }
}
