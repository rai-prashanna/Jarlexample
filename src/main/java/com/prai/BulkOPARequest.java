package com.prai;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "input"
})
@Generated("jsonschema2pojo")
public class BulkOPARequest implements Serializable
{

    @Override
    public String toString() {
        return "BulkOPARequest{" +
                "input=" + input +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BulkOPARequest that = (BulkOPARequest) o;

        if (!input.equals(that.input)) return false;
        if (!additionalProperties.equals(that.additionalProperties)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = input.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @JsonProperty("input")
    private BulkOPAInput input;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 1817343865301079127L;

    /**
     * No args constructor for use in serialization
     *
     */
    public BulkOPARequest() {
    }

    /**
     *
     * @param input
     */
    public BulkOPARequest(BulkOPAInput input) {
        super();
        this.input = input;
    }

    @JsonProperty("input")
    public BulkOPAInput getInput() {
        return input;
    }

    @JsonProperty("input")
    public void setInput(BulkOPAInput input) {
        this.input = input;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}

