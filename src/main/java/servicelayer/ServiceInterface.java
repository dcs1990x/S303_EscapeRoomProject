package servicelayer;

public interface ServiceInterface<T> {
    T obtainEntity(Long id);
    void createEntity()
}
