package enumerations;


public enum ImportanceEnum {

    IMPORTANCE_INCONNUE(0),
    IMPORTANCE_PRINCIPALE(1),
    IMPORTANCE_SECONDAIRE(2);

    private int importance;

    private ImportanceEnum(int importance){
        this.importance=importance;
    }

    public int getImportance(){
        return importance;
    }
}
