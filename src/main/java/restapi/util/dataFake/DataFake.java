package restapi.util.dataFake;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DataFake<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    abstract protected E getData();

    public Collection<E> getDataList(int numberRecords) {
        List<E> resultList = new ArrayList<E>();
        for (int i = 0; i < numberRecords; i++) {
            resultList.add(getData());
        }
        return resultList;
    }
}
