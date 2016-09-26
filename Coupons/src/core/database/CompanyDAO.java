package core.database;

import java.util.Collection;

import core.database.model.CompanyDO;
import core.database.model.CouponDO;

public interface CompanyDAO {

	int createCompany(CompanyDO company);

	int removeCompany(CompanyDO company);

	int updateCompany(CompanyDO company);

	CompanyDO getCompany(long companyId);

	Collection<CompanyDO> getAllCompanies();

	Collection<CouponDO> getCoupons(long companyId);

	boolean login(long companyId, String password);

}
