package businesslogic;

import domain.Blueprint;

/**
 * 
 * @author daniCV
 */

public interface IBlueprintDAO {
    public int addBlueprint(Blueprint blueprint);
    public int modifyBlueprint(Blueprint blueprint);
    public Blueprint consultBlueprintByTitle(String blueprintTitle);
    public Blueprint consultBlueprintByDate(String blueprintDate);
    public int deleteBlueprintByTitle(String blueprintTitle);
}