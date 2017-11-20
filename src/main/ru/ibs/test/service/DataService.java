package main.ru.ibs.test.service;

import main.ru.ibs.test.dto.Claim;
import main.ru.ibs.test.dto.ComboBoxElem;
import main.ru.ibs.test.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class DataService {

    private static final Logger logger = Logger.getLogger(DataService.class.getName());
    Map<Long, String> personMap;
    Map<Long, String> statusMap;
    Map<Long, Claim> claimMap;
    AtomicLong claimSequence;

    DataService() {
        personMap = new ConcurrentHashMap<>();
        personMap.put(1L, "Max");
        personMap.put(2L, "Sasha");
        personMap.put(3L, "Jack");

        statusMap = new ConcurrentHashMap<>();
        statusMap.put(1L, "New");
        statusMap.put(2L, "In work");
        statusMap.put(3L, "Archive");

        claimSequence = new AtomicLong();

        claimMap = new ConcurrentHashMap<>();
        Long claimId = getNextClaimId();
        claimMap.put(claimId, new Claim(claimId, 10, "Test claim", 2L, 3L, 3L));
        claimId = getNextClaimId();
        claimMap.put(claimId, new Claim(claimId, 15, "Another test claim", 1L, 3L, 2L));
        claimId = getNextClaimId();
        claimMap.put(claimId, new Claim(claimId, 16, "Third test claim", 3L, 1L, 1L));
    }

    public Long getNextClaimId() {
        return claimSequence.incrementAndGet();
    }

    public List<ComboBoxElem> getAllStatuses() {
        return statusMap
                .entrySet()
                .stream()
                .map(longStringEntry -> {
                    return new ComboBoxElem(longStringEntry.getKey(), longStringEntry.getValue());
                })
                .collect(Collectors.toList());
    }

    public List<ComboBoxElem> getAllPersons() {
        return personMap
                .entrySet()
                .stream()
                .map(longStringEntry -> {
                    return new ComboBoxElem(longStringEntry.getKey(), longStringEntry.getValue());
                })
                .collect(Collectors.toList());
    }

    public List<Claim> getAllClaims() {
        logger.info("Read all claims");
        return claimMap
                .entrySet()
                .stream()
                .map(longClaimEntry -> {
                    Claim claim = longClaimEntry.getValue();
                    return new Claim(longClaimEntry.getKey(),
                            claim.getNumber(),
                            claim.getName(),
                            claim.getFrom(),
                            claim.getTo(),
                            claim.getStatus());
                })
                .collect(Collectors.toList());
    }

    public Claim getClaim(Long id) throws RuntimeException {
        if (!claimMap.containsKey(id)) {
            logger.info("Claim id=" + id + " not found");
            throw new NotFoundException(id);
        }
        logger.info("Read claim id=" + id + " payload:" + claimMap.get(id).toString());
        return claimMap.get(id);
    }

    public Claim updateClaim(Long id, Claim claim) throws RuntimeException {
        if (!claimMap.containsKey(id)) {
            logger.info("Claim id=" + id + " not found for update");
            throw new NotFoundException(id);
        }
        logger.info("Update claim id=" + id + " payload:" + claim.toString());
        claimMap.put(id, claim);
        return new Claim(id, claim.getNumber(), claim.getName(),
                claim.getFrom(), claim.getTo(), claim.getStatus());
    }

    public Claim createClaim(Claim claim) {
        Long newId = getNextClaimId();
        claimMap.put(newId, claim);
        logger.info("Create claim id=" + newId + " payload:" + claim.toString());
        return new Claim(newId, claim.getNumber(), claim.getName(),
                claim.getFrom(), claim.getTo(), claim.getStatus());
    }

    public void removeClaim(Long id) throws RuntimeException {
        if (!claimMap.containsKey(id)) {
            logger.info("Claim id=" + id + " not found for remove");
            throw new NotFoundException(id);
        }
        logger.info("Remove claim id=" + id);
        claimMap.remove(id);
    }

}
