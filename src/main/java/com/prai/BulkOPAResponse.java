package com.prai;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "result"
})
@Generated("jsonschema2pojo")
public class BulkOPAResponse implements Serializable
{

    @JsonProperty("result")
    private BulkOPADecision result;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 7619976758895537290L;

    /**
     * No args constructor for use in serialization
     *
     */
    public BulkOPAResponse() {
    }

    /**
     *
     * @param result
     */
    public BulkOPAResponse(BulkOPADecision result) {
        super();
        this.result = result;
    }

    @JsonProperty("result")
    public BulkOPADecision getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(BulkOPADecision result) {
        this.result = result;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BulkOPAResponse that = (BulkOPAResponse) o;

        if (!result.equals(that.result)) return false;
        if (!additionalProperties.equals(that.additionalProperties)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = result.hashCode();
        result1 = 31 * result1 + additionalProperties.hashCode();
        return result1;
    }

    @Override
    public String toString() {
        return "BulkOPAResponse{" +
                "result=" + result +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}