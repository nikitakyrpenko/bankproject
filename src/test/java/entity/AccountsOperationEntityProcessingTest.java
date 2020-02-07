package entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AccountsOperationEntityProcessingTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    public AccountEntity sender;
    public AccountEntity receiver;
    public OperationEntity operationEntity;

    @Parameterized.Parameter(0)
    public Double transfer;

    @Parameterized.Parameter(1)
    public Double senderAccountBalance;

    @Parameterized.Parameter(2)
    public Double receiverAccountBalance;

    @Parameterized.Parameter(3)
    public Double expectedSenderBalance;

    @Parameterized.Parameter(4)
    public Double expectedReceiverBalance;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(
                new Object[][]{
                        {45.90, 1000.0, 130.0, 954.10, 175.90},
                        {1200.0, 5780.0, 0.0, 4580.0, 1200.0},
                        {0.54, 1.0, 0.0, 0.46, 0.54}
                }
        );
    }

    @Before
    public void init(){
        sender = new DepositAccountEntity.DepositAccountBuilder()
                .withDepositAmount(senderAccountBalance)
                .withDepositRate(0.1)
                .build();

        receiver = new CreditAccountEntity.CreditAccountBuilder()
                .withCreditLimit(receiverAccountBalance)
                .withCreditRate(0.1)
                .build();

        operationEntity = OperationEntity.builder()
                .withTransfer(transfer)
                .withSender(sender)
                .withReceiver(receiver)
                .build();

       /* sender.processTransfer(operationEntity);
        receiver.processTransfer(operationEntity);*/
    }

    @Test
    public void getBalanceShouldReturnProperBalanceAfterTransactionAndContainItAfterOperationBeingProcessed(){
        assertEquals(expectedReceiverBalance,receiver.getBalance(),0.01);
        assertEquals(expectedSenderBalance,sender.getBalance(),0.01);
        /*assertTrue(sender.getOperations().contains(operation));
        assertTrue(receiver.getOperations().contains(operation));*/
    }




}
