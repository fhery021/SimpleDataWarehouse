package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import com.example.simpledatawarehouse.repository.TimeDimensionRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimeDimensionServiceImpl implements TimeDimensionService {

    private final TimeDimensionRepository timeDimensionRepository;

    public TimeDimensionServiceImpl(TimeDimensionRepository timeDimensionRepository) {
        this.timeDimensionRepository = timeDimensionRepository;
    }

    @Override
    public TimeDimensionEntity save(TimeDimensionEntity timeDimensionEntity) {
        return timeDimensionRepository.save(timeDimensionEntity);
    }

    @Override
    public TimeDimensionEntity saveIfNew(TimeDimensionEntity timeDimensionEntity) {
        Optional<TimeDimensionEntity> timeDimensionEntityFound = findOneByExample(timeDimensionEntity);
        if (timeDimensionEntityFound.isEmpty()) {
            return timeDimensionRepository.save(timeDimensionEntity);
        } else {
            return timeDimensionEntityFound.orElseThrow();
        }
    }

    @Override
    public List<TimeDimensionEntity> findAllByDateFilterOrInterval(LocalDate dateFilter, LocalDate fromDate, LocalDate toDate) {
        List<TimeDimensionEntity> timeDimensionEntities;
        if (dateFilter != null) {
            timeDimensionEntities = findAllByDate(dateFilter);
        } else {
            // TODO validation before this level
            timeDimensionEntities = findByDateBetween(fromDate, toDate);
        }
        return timeDimensionEntities;
    }

    private List<TimeDimensionEntity> findAllByDate(LocalDate date) {
        if (date == null) {
            return timeDimensionRepository.findAll();
        }
        TimeDimensionEntity exampleEntity = new TimeDimensionEntity();
        exampleEntity.setDate(date);

        return findAllByExample(exampleEntity);
    }

    private List<TimeDimensionEntity> findByDateBetween(LocalDate fromDate, LocalDate toDate) {
        return timeDimensionRepository.findByDateBetween(fromDate, toDate);
    }

    private Optional<TimeDimensionEntity> findOneByExample(TimeDimensionEntity entity) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id", "metricsEntityList");
        Example<TimeDimensionEntity> example = Example.of(entity, exampleMatcher);

        return timeDimensionRepository.findOne(example);
    }

    private List<TimeDimensionEntity> findAllByExample(TimeDimensionEntity entity) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id", "metricsEntityList");
        Example<TimeDimensionEntity> example = Example.of(entity, exampleMatcher);

        return timeDimensionRepository.findAll(example);
    }


}
