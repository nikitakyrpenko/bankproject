package domain.abstraction;

import entity.OperationEntity;

public interface OperationProcessable {

    void processTransfer(OperationEntity operationEntity);
}
