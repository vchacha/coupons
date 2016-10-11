package core.database;

import java.util.Collection;

import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.Type;

public interface CompanyDAO {

	int createCompany(CompanyDO company);

	int removeCompany(CompanyDO company);

	int updateCompany(CompanyDO company);

	CompanyDO getCompany(long companyId);

	Collection<CompanyDO> getAllCompanies();

	Collection<CouponDO> getAllCouponsByCompany(long companyId);
	
	Collection<CouponDO> getAllCouponsByCompanyAndType(long companyId, Type type);
	
	boolean login(long companyId, String password);

}
