package towergame.entities;

import java.util.Comparator;

public class EntityComparator implements Comparator<Entity>{

	@Override
	public int compare(Entity entity1, Entity entity2) {
		if (entity1.getPosition().getX() > entity2.getPosition().getX()){
			return -1;
		} else if (entity1.getPosition().getX() > entity2.getPosition().getX()){
			return 1;
		} else {
			return 0;
		}
	}

}
