import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Ministry {

    public LinkedList<Object> getSites(LinkedList<Groups> groups){
        return groups.stream()
                .map(Groups::getSite)
                .distinct()
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Site> getClosedSites(LinkedList<Groups> groups){
        LinkedList<Site> closedSites = new LinkedList<>();
        LinkedList<Object> sites = getSites(groups);

        LinkedList<Groups> westernWallGroups = groups.stream()
                .filter(p -> p.getSite().equals(TouristsDistribution.westernWall)).collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Groups> bahaiGardensGroups = groups.stream()
                .filter(p -> p.getSite().equals(TouristsDistribution.bahaiGardens)).collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Groups> miniIsraelGroups = groups.stream()
                .filter(p -> p.getSite().equals(TouristsDistribution.miniIsrael)).collect(Collectors.toCollection(LinkedList::new));

        int westernWallcapasity = 0;
        int bahaiGardenscapasity = 0;
        int miniIsraelcapasity = 0;
        int i = 0;
        while (i < sites.size()) {
            Site site = (Site) sites.get(i);
            switch (site.getName()){
                case "Western Wall": westernWallcapasity = site.getCapacity();
                case "Baha'i Gardens": bahaiGardenscapasity = site.getCapacity();
                case "Mini Israel": miniIsraelcapasity = site.getCapacity();
                i ++;
            }
        }
//Get capacity of groups per choosen site
        List<Integer> wwGroupsCapacity = getGroupsCapacityPerSite(westernWallGroups);
        List<Integer> bahaiGroupsCapacity = getGroupsCapacityPerSite(bahaiGardensGroups);
        List<Integer> misraelGroupsCapacity = getGroupsCapacityPerSite(miniIsraelGroups);

//Check if sites are closed
        Site ww = isSiteClosed(wwGroupsCapacity, TouristsDistribution.westernWall, westernWallcapasity);
        if(ww != null){
            closedSites.add(ww);
        }

        return closedSites;
    }

    private List<Integer> getGroupsCapacityPerSite(LinkedList<Groups> siteGroups){
        List<Integer> groupsCapacity = new ArrayList<>();
        for (Groups group : siteGroups) {
            groupsCapacity.add(group.getCapacity());
        }
        return groupsCapacity;
    }

    private Site isSiteClosed(List<Integer> groupsCapacity, Site site, int siteCapacity){
        int sum = 0;
        Site closedSite = null;
        for (Integer integer : groupsCapacity) {
            sum += integer;
            if (sum >= siteCapacity * 0.9 && sum <= siteCapacity) {
                closedSite = site;
            }
        }
        return closedSite;
    }
}

class TouristsDistribution {
    final static WesternWall westernWall = new WesternWall();
    static BahaiGardens bahaiGardens = new BahaiGardens();
    static MiniIsrael miniIsrael = new MiniIsrael();
    public static void main(String[] args) {
        Groups gr1 = new Groups(5, "USA", westernWall);
        Groups gr2 = new Groups(7, "Russia", bahaiGardens);
        Groups gr3 = new Groups(9, "India", miniIsrael);
        Groups gr4 = new Groups(15, "England", miniIsrael);
        Groups gr5 = new Groups(22, "Turkey", westernWall);
        Groups gr6 = new Groups(12, "France", westernWall);
        Groups gr7 = new Groups(17, "Ukraine", bahaiGardens);
        Groups gr8 = new Groups(21, "Germany", miniIsrael);

        LinkedList<Groups> groups = new LinkedList<Groups>();
        groups.add(gr1);
        groups.add(gr2);
        groups.add(gr3);
        groups.add(gr4);
        groups.add(gr5);
        groups.add(gr6);
        groups.add(gr7);
        groups.add(gr8);

        Ministry ministry = new Ministry();
        LinkedList<Object> sites;

        sites = ministry.getSites(groups);

        System.out.println(sites);

        LinkedList<Site> closedSites = ministry.getClosedSites(groups);
    }
}
