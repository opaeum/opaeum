package org.nakeduml.runtime.domain;


public enum RevisionType {
    ADD((byte) 0),
    MOD((byte) 1);
    private Byte representation;
    private RevisionType(byte representation) {
        this.representation = representation;
    } 
    public static RevisionType fromRepresentation(Object representation) {
        if (representation == null || !(representation instanceof Byte)) {
            return null;
        }
        switch ((Byte) representation) {
            case 0: return ADD;
            case 1: return MOD;
        }
        throw new IllegalArgumentException("Unknown representation: " + representation);
    }    
}
