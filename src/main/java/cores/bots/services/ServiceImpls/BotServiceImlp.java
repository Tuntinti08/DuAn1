package cores.bots.services.ServiceImpls;

import cores.bots.repositoris.BotRepository;
import cores.bots.services.BotService;

/**
 *
 * @author QUOC HUY
 */
public class BotServiceImlp implements BotService{
    
    private BotRepository rp;

    public BotServiceImlp() {
        rp = new BotRepository();
    }
    
    @Override
    public boolean updateTrangThai() {
        return rp.updateTrangThai();
    }
    
}
