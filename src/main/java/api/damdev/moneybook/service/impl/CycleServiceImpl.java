package api.damdev.moneybook.service.impl;

import api.damdev.moneybook.common.type.ActiveType;
import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.cycle.CycleInfo;
import api.damdev.moneybook.repository.CycleRepo;
import api.damdev.moneybook.service.CycleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("cycleService")
public class CycleServiceImpl implements CycleService {

    @Resource
    CycleRepo cycleRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Cycle insertCycle(CycleInfo addInfo) {
//        Cycle entity = new Cycle(addInfo);

        Cycle entity = modelMapper.map(addInfo, Cycle.class);

        return cycleRepo.save(entity);
    }

    @Override
    public Cycle updateCycle(CycleInfo addInfo, String id) {
//        Cycle entity = new Cycle(addInfo, id);

        Cycle entity = modelMapper.map(addInfo, Cycle.class);
        entity.setId(id);

        return cycleRepo.save(entity);
    }

    @Override
    public Cycle deleteCycle(String id) {
        Cycle entity = cycleRepo.getOne(id);
        entity.setActive(ActiveType.INACTIVE);

        return cycleRepo.save(entity);
    }
}
