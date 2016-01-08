package engine.hashFunction;

/**
 * Created by Julien Hatin on 09/12/15.
 *
 * Handle a hash
 *
 */
public class Hash {

    private String hash;

    /**
     * Construct a hash handler
     * @param hash the hash to be stored
     */
    public Hash(String hash) {
        this.hash = hash;
    }

    /**
     *
     * @return the stored hash
     */
    public String getHash() {
        return hash;
    }

    /**
     *
     * @param hash the new hash to store
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Hash{" +
                "hash='" + hash + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hash hash1 = (Hash) o;

        return !(hash != null ? !hash.equals(hash1.hash) : hash1.hash != null);

    }

    @Override
    public int hashCode() {
        return hash != null ? hash.hashCode() : 0;
    }
}
