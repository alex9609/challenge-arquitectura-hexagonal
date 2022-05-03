package co.com.bvc.st.challenge.microservicio.challenge.infraestructure.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "producto")
public class ProductDynamo {

    @DynamoDBHashKey
    private int id_product;

    @DynamoDBAttribute
    private String nameProduct;

    @DynamoDBAttribute
    private String typeProduct;

}